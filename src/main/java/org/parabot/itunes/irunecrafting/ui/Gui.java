package org.parabot.itunes.irunecrafting.ui;

import org.parabot.itunes.irunecrafting.core.Core;

import javax.swing.*;
import java.util.Objects;

/**
 * Created by Tristan on 4/09/2018.
 */
public class Gui extends JFrame {
    private JPanel panelMain;
    private JComboBox cmbRunes;
    private JButton btnStart;
    private JLabel lblMode;
    private JLabel lblTitle;
    private JLabel lblSubTitle;
    private JComboBox cmbMode;

    public Gui() {
        setTitle("Configure script");
        setContentPane(panelMain);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        btnStart.addActionListener(e -> {
            Core.getSettings().setRunes(Objects.requireNonNull(cmbRunes.getSelectedItem()).toString());
            Core.getSettings().setMode(Objects.requireNonNull(cmbMode.getSelectedItem().toString()));
            dispose();
        });
    }
}
