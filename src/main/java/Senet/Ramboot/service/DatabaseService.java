package Senet.Ramboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {


    @Autowired
    TipousuarioService oTipousuarioService;

    @Autowired
    UsuarioService oUsuarioService;

    @Autowired
    ZonaService oZonaService;

    @Autowired
    BebidaService oBebidaService;

    @Autowired
    SnackService oSnackService;

    @Autowired
    GcontrataService oGcontrataService;

    @Autowired
    BonoService oBonoService;




    public Long fill() {

        oTipousuarioService.randomCreate(0L);
        oUsuarioService.randomCreate(50L);
        oZonaService.randomCreate(0L);
        oBebidaService.randomCreate(10L);
        oSnackService.randomCreate(10L); 
        oGcontrataService.randomCreate(20L);
        oBonoService.randomCreate(0L);
        return 0L;
    }

}
