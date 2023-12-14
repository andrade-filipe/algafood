package com.esr.algafood.aulas.aula226;

@Component
@ConfigurationProperties("notificador.email")
public class ConfigProperties {

    //para utilizar basta injetar essa classe em qualquer lugar

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
