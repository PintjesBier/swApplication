package org.parabot.itunes.irunecrafting.ui;

import org.parabot.itunes.irunecrafting.data.Selector;
import org.parabot.itunes.irunecrafting.data.Settings;
import org.rev317.min.api.methods.Skill;

import javax.swing.*;

/**
 * Created by Tristan on 4/09/2018.
 */
public class Gui extends JFrame {
    private Settings settings;

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
            settings = new Settings(Selector.correspondingAltar(Skill.RUNECRAFTING.getRealLevel(), cmbRunes.getSelectedItem().toString()), false, false, "Waiting...", cmbRunes.getSelectedItem().toString(), cmbMode.getSelectedItem().toString(), 0);
            dispose();
        });
    }

    public Settings getSettings() {
        return settings;
    }
}
