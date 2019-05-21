package org.parabot.itunes.irunecrafting.gui;

import org.parabot.itunes.irunecrafting.core.Core;

import javax.swing.*;
import java.util.Objects;

/**
 * Created by Tristan on 4/09/2018.
 */
public class gui extends JFrame {
    private JPanel panelMain;
    private JComboBox cmbRunes;
    private JButton btnStart;
    private JLabel lblMode;
    private JLabel lblTitle;
    private JLabel lblSubTitle;
    private JComboBox cmbMode;

    public gui() {
        setTitle("Configure script");
        setContentPane(panelMain);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        btnStart.addActionListener(e -> {
            Core.runes = Objects.requireNonNull(cmbRunes.getSelectedItem()).toString();
            Core.mode = Objects.requireNonNull(cmbMode.getSelectedItem().toString());
            dispose();
        });
    }
}
