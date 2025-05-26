package Senet.Ramboot.service;

import Senet.Ramboot.Enum.EstadoOrdenador;
import Senet.Ramboot.entity.OrdenadorEntity;
import Senet.Ramboot.entity.UsuarioEntity;
import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.repository.OrdenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;


@Service
public class OrdenadorService {

    @Autowired
    private OrdenadorRepository ordenadorRepository;

    // ------------------------------
    // MÉTODOS CRUD
    // ------------------------------

    public List<OrdenadorEntity> findAll() {
        return ordenadorRepository.findAll();
    }

    public OrdenadorEntity findById(Long id) {
        return ordenadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ordenador no encontrado con ID: " + id));
    }

    public OrdenadorEntity save(OrdenadorEntity ordenador) {
        return ordenadorRepository.save(ordenador);
    }

public void actualizarEstadoOrdenador(Long idOrdenador, EstadoOrdenador nuevoEstado) {
    OrdenadorEntity ordenador = findById(idOrdenador);
    ordenador.setEstado(nuevoEstado);
    ordenadorRepository.save(ordenador);
}

public void marcarOcupadoPorUsuario(Long idOrdenador, Long idUsuario) {
    OrdenadorEntity ordenador = findById(idOrdenador);
    if (ordenador.getUsuario() != null && !ordenador.getUsuario().getId().equals(idUsuario)) {
        throw new IllegalStateException("Este ordenador ya está asignado a otro usuario.");
    }
    ordenador.setUsuario(new UsuarioEntity(idUsuario)); // solo necesitas setear el ID
    ordenador.setEstado(EstadoOrdenador.OCUPADO);
    ordenadorRepository.save(ordenador);
}

public void liberarOrdenador(Long idOrdenador) {
    OrdenadorEntity ordenador = findById(idOrdenador);
    ordenador.setUsuario(null);
    ordenador.setEstado(EstadoOrdenador.LIBRE);
    ordenadorRepository.save(ordenador);
}


    public OrdenadorEntity update(Long id, OrdenadorEntity ordenadorActualizado) {
        OrdenadorEntity ordenadorExistente = findById(id);
        ordenadorExistente.setNombre(ordenadorActualizado.getNombre());
        ordenadorExistente.setMacAddress(ordenadorActualizado.getMacAddress());
        ordenadorExistente.setIpAddress(ordenadorActualizado.getIpAddress());
        ordenadorExistente.setUsuario(ordenadorActualizado.getUsuario());
        return ordenadorRepository.save(ordenadorExistente);
    }

    public void delete(Long id) {
        if (!ordenadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ordenador no encontrado con ID: " + id);
        }
        ordenadorRepository.deleteById(id);
    }

    // ------------------------------
    // MAGIC PACKET
    // ------------------------------

    public void enviarMagicPacket(String macAddress) throws Exception {
        byte[] macBytes = getMacBytes(macAddress);
        byte[] packetData = new byte[6 + 16 * macBytes.length];

        // Primeros 6 bytes FF
        Arrays.fill(packetData, 0, 6, (byte) 0xFF);

        // Repetir la MAC 16 veces
        for (int i = 6; i < packetData.length; i += macBytes.length) {
            System.arraycopy(macBytes, 0, packetData, i, macBytes.length);
        }

        // Enviar broadcast
        InetAddress address = InetAddress.getByName("255.255.255.255");
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, address, 9);
        DatagramSocket socket = new DatagramSocket();
        socket.setBroadcast(true);
        socket.send(packet);
        socket.close();
    }

    private byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("[-:]");
        if (hex.length != 6) throw new IllegalArgumentException("MAC inválida: " + macStr);
        for (int i = 0; i < 6; i++) {
            bytes[i] = (byte) Integer.parseInt(hex[i], 16);
        }
        return bytes;
    }
}
