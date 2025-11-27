package gm.Ajedrez;

import com.formdev.flatlaf.FlatDarculaLaf;

import gm.Ajedrez.gui.AjedrezForma;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class AjedrezSwing {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();

        ConfigurableApplicationContext contextoSpring = new SpringApplicationBuilder(AjedrezSwing.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
        SwingUtilities.invokeLater(() -> {
            AjedrezForma ajedrezForma = contextoSpring.getBean(AjedrezForma.class);
            ajedrezForma.setVisible(true);
        });
    }
}
