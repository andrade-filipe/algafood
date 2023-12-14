package com.esr.algafood.aulas.aula226;

@Component
@ConfigurationProperties("notificador.email")
public class ConfigProperties {

    private String hostServidor;
    private Integer portaServidor

    public String getHostServidor() {
        return hostServidor;
    }

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public Integer getPortaServidor() {
        return portaServidor;
    }

    public void setPortaServidor(Integer portaServidor) {
        this.portaServidor = portaServidor;
    }
}
