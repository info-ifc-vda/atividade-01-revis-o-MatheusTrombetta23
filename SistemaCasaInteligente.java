import java.util.ArrayList;
import java.util.List;


abstract class Dispositivo {
    protected String id;
    protected String localizacao;
    protected boolean ligado;
    
    public Dispositivo(String id, String localizacao) {
        this.id = id;
        this.localizacao = localizacao;
        this.ligado = false;
    }
    
    // Métodos concretos
    public void ligar() {
        this.ligado = true;
        System.out.println(id + " ligado(a)");
    }
    
    public void desligar() {
        this.ligado = false;
        System.out.println(id + " desligado(a)");
    }
    
    public void exibirStatus() {
        System.out.println("ID: " + id + " | Local: " + localizacao + " | Status: " + (ligado ? "Ligado" : "Desligado"));
    }
    
    public boolean estaLigado() {
        return ligado;
    }
    
    
    public abstract void funcionar();
    
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
}

// INTERFACES
interface Conectavel {
    void conectarWifi();
    void desconectarWifi();
}

interface Atualizavel {
    void atualizarFirmware();
}

interface Monitoravel {
    void gerarRelatorio();
}

interface Agendavel {
    void agendarAtividade(String horario, String atividade);
    void cancelarAgendamento();
}


class LampadaInteligente extends Dispositivo implements Conectavel, Agendavel {
    private String cor;
    private int intensidade;
    private String agendamento;
    
    public LampadaInteligente(String id, String localizacao) {
        super(id, localizacao);
        this.cor = "Branca";
        this.intensidade = 100;
    }
    
    @Override
    public void funcionar() {
        if (estaLigado()) {
            System.out.println("Lâmpada " + id + " funcionando - Cor: " + cor + ", Intensidade: " + intensidade + "%");
        } else {
            System.out.println("Lâmpada " + id + " desligada");
        }
    }
    
    @Override
    public void conectarWifi() {
        System.out.println("Lâmpada " + id + " conectada ao WiFi");
    }
    
    @Override
    public void desconectarWifi() {
        System.out.println("Lâmpada " + id + " desconectada do WiFi");
    }
    
    @Override
    public void agendarAtividade(String horario, String atividade) {
        this.agendamento = horario + " - " + atividade;
        System.out.println("Lâmpada " + id + " agendada para: " + agendamento);
    }
    
    @Override
    public void cancelarAgendamento() {
        this.agendamento = null;
        System.out.println("Agendamento da lâmpada " + id + " cancelado");
    }
    
    // Métodos específicos
    public void setCor(String cor) {
        this.cor = cor;
        System.out.println("Cor da lâmpada " + id + " alterada para " + cor);
    }
    
    public void setIntensidade(int intensidade) {
        this.intensidade = Math.min(100, Math.max(0, intensidade));
        System.out.println("Intensidade da lâmpada " + id + " alterada para " + this.intensidade + "%");
    }
}


class Termostato extends Dispositivo implements Conectavel, Monitoravel {
    private double temperaturaAlvo;
    private double temperaturaAtual;
    
    public Termostato(String id, String localizacao) {
        super(id, localizacao);
        this.temperaturaAlvo = 22.0;
        this.temperaturaAtual = 20.0;
    }
    
    @Override
    public void funcionar() {
        if (estaLigado()) {
            if (temperaturaAtual < temperaturaAlvo) {
                temperaturaAtual += 0.5;
                System.out.println("Termostato " + id + " aquecendo... Temp atual: " + temperaturaAtual + "°C");
            } else if (temperaturaAtual > temperaturaAlvo) {
                temperaturaAtual -= 0.5;
                System.out.println("Termostato " + id + " resfriando... Temp atual: " + temperaturaAtual + "°C");
            } else {
                System.out.println("Termostato " + id + " - Temperatura estabilizada em " + temperaturaAtual + "°C");
            }
        } else {
            System.out.println("Termostato " + id + " desligado");
        }
    }
    
    @Override
    public void conectarWifi() {
        System.out.println("Termostato " + id + " conectado ao WiFi");
    }
    
    @Override
    public void desconectarWifi() {
        System.out.println("Termostato " + id + " desconectado do WiFi");
    }
    
    @Override
    public void gerarRelatorio() {
        System.out.println("=== RELATORIO DO TERMOSTATO " + id + " ===");
        System.out.println("Localizaçao: " + localizacao);
        System.out.println("Temperatura Alvo: " + temperaturaAlvo + "°C");
        System.out.println("Temperatura Atual: " + temperaturaAtual + "°C");
        System.out.println("Status: " + (ligado ? "Ligado" : "Desligado"));
    }
    
    public void setTemperaturaAlvo(double temperatura) {
        this.temperaturaAlvo = temperatura;
        System.out.println("Temperatura alvo do termostato " + id + " alterada para " + temperatura + "°C");
    }
}


class CameraSeguranca extends Dispositivo implements Conectavel, Monitoravel, Atualizavel {
    private boolean gravando;
    
    public CameraSeguranca(String id, String localizacao) {
        super(id, localizacao);
        this.gravando = false;
    }
    
    @Override
    public void funcionar() {
        if (estaLigado()) {
            if (gravando) {
                System.out.println("Camera " + id + " gravando em " + localizacao);
            } else {
                System.out.println("Camera " + id + " em modo de espera em " + localizacao);
            }
        } else {
            System.out.println("Camera " + id + " desligada");
        }
    }
    
    @Override
    public void conectarWifi() {
        System.out.println("Camera " + id + " conectada ao WiFi");
    }
    
    @Override
    public void desconectarWifi() {
        System.out.println("Camera " + id + " desconectada do WiFi");
    }
    
    @Override
    public void gerarRelatorio() {
        System.out.println("=== RELATORIO DA CAMERA " + id + " ===");
        System.out.println("Localizaçao: " + localizacao);
        System.out.println("Status: " + (ligado ? "Ligado" : "Desligado"));
        System.out.println("Gravando: " + (gravando ? "Sim" : "Não"));
    }
    
    @Override
    public void atualizarFirmware() {
        System.out.println("Atualizando firmware da camera " + id + "...");
        System.out.println("Firmware da camera " + id + " atualizado com sucesso!");
    }
    
    public void iniciarGravacao() {
        if (estaLigado()) {
            this.gravando = true;
            System.out.println("Camera " + id + " iniciou gravaçao em " + localizacao);
        }
    }
    
    public void pararGravacao() {
        this.gravando = false;
        System.out.println("Camera " + id + " parou gravaçao");
    }
}


class Fechadura extends Dispositivo implements Conectavel, Agendavel {
    private boolean trancada;
    private String agendamento;
    
    public Fechadura(String id, String localizacao) {
        super(id, localizacao);
        this.trancada = true;
    }
    
    @Override
    public void funcionar() {
        if (estaLigado()) {
            System.out.println("Fechadura " + id + " - Status: " + (trancada ? "Trancada" : "Destrancada"));
        } else {
            System.out.println("Fechadura " + id + " desligada");
        }
    }
    
    @Override
    public void conectarWifi() {
        System.out.println("Fechadura " + id + " conectada ao WiFi");
    }
    
    @Override
    public void desconectarWifi() {
        System.out.println("Fechadura " + id + " desconectada do WiFi");
    }
    
    @Override
    public void agendarAtividade(String horario, String atividade) {
        this.agendamento = horario + " - " + atividade;
        System.out.println("Fechadura " + id + " agendada para: " + agendamento);
    }
    
    @Override
    public void cancelarAgendamento() {
        this.agendamento = null;
        System.out.println("Agendamento da fechadura " + id + " cancelado");
    }
    
    public void trancar() {
        this.trancada = true;
        System.out.println("Fechadura " + id + " trancada");
    }
    
    public void destrancar() {
        this.trancada = false;
        System.out.println("Fechadura " + id + " destrancada");
    }
}

// CLASSE PRINCIPAL PARA EXECUÇÃO
public class SistemaCasaInteligente {
    public static void main(String[] args) {
        // 1. Criar lista de dispositivos
        List<Dispositivo> dispositivos = new ArrayList<>();
        
        
        LampadaInteligente lampada = new LampadaInteligente("LAMP001", "Sala");
        Termostato termostato = new Termostato("TERM001", "Quarto");
        CameraSeguranca camera = new CameraSeguranca("CAM001", "Entrada");
        Fechadura fechadura = new Fechadura("FECH001", "Porta Principal");
        
        
        dispositivos.add(lampada);
        dispositivos.add(termostato);
        dispositivos.add(camera);
        dispositivos.add(fechadura);
        
        
        lampada.conectarWifi();
        termostato.conectarWifi();
        camera.conectarWifi();
        fechadura.conectarWifi();
        
        System.out.println("\n=== INICIANDO SISTEMA ===\n");
        
        
        for (Dispositivo dispositivo : dispositivos) {
            System.out.println("--- Processando dispositivo: " + dispositivo.getId() + " ---");
            
            
            dispositivo.ligar();
            
            
            dispositivo.funcionar();
            
            
            dispositivo.exibirStatus();
            
            
            switch (dispositivo) {
                case LampadaInteligente lamp -> {
                    lamp.setCor("Azul");
                    lamp.setIntensidade(75);
                    lamp.agendarAtividade("20:00", "Acender automaticamente");
                    lamp.funcionar();
                    
                }
                case Termostato term -> {
                    term.setTemperaturaAlvo(24.0);
                    term.gerarRelatorio();
                    
                }
                case CameraSeguranca cam -> {
                    cam.iniciarGravacao();
                    cam.gerarRelatorio();
                    cam.atualizarFirmware();
                    
                }
                case Fechadura fech -> {
                    fech.destrancar();
                    fech.agendarAtividade("23:00", "Trancar automaticamente");
                }
                default -> {
                }
            }
            
            System.out.println();
        }
        
        System.out.println("=== RESUMO FINAL DOS DISPOSITIVOS ===");
        for (Dispositivo dispositivo : dispositivos) {
            dispositivo.exibirStatus();
            dispositivo.funcionar();
        }
    }
}