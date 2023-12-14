package com.esr.algafood.aulas.aula222;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoListener {

    /*
     * @TipoDoNotificador(NivelUrgencia.URGENTE)
     * @Autowired
     * private Notificador notificador;
     * */
    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent clienteAtivadoEvent){
        System.out.println(clienteAtivadoEvent.getCliente());
    }

    /*
    * Caso de uso: desacoplar a notificação da classe de negócio.
    * várias notificações podem ser mandadas ao mesmo tempo sem repetir
    * código*/
}
