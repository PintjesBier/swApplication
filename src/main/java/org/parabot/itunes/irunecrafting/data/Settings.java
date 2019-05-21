package org.parabot.itunes.irunecrafting.data;
public class Settings
{
    private Selector currentAltar;
    private boolean dataGathered;
    private boolean needsBanking;
    private String currentStatus;
    private String runes;
    private String mode;
    private int runesCrafted;

    public Settings(Selector currentAltar, boolean dataGathered, boolean needsBanking, String currentStatus, String runes, String mode, int runesCrafted)
    {
        this.currentAltar = currentAltar;
        this.dataGathered = dataGathered;
        this.needsBanking = needsBanking;
        this.currentStatus = currentStatus;
        this.runes = runes;
        this.mode = mode;
        this.runesCrafted = runesCrafted;
    }

    public Selector getCurrentAltar()
    {
        return currentAltar;
    }

    public boolean dataIsGathered()
    {
        return dataGathered;
    }

    public void setDataGathered(boolean dataGathered)
    {
        this.dataGathered = dataGathered;
    }

    public boolean needsBanking()
    {
        return needsBanking;
    }

    public void setNeedsBanking(boolean needsBanking)
    {
        this.needsBanking = needsBanking;
    }

    public void setCurrentAltar(Selector currentAltar)
    {
        this.currentAltar = currentAltar;
    }

    public String getCurrentStatus()
    {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus)
    {
        this.currentStatus = currentStatus;
    }

    public String getRunes()
    {
        return runes;
    }

    public void setRunes(String runes)
    {
        this.runes = runes;
    }

    public String getMode()
    {
        return mode;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public int getRunesCrafted()
    {
        return runesCrafted;
    }

    public void setRunesCrafted(int runesCrafted)
    {
        this.runesCrafted = runesCrafted;
    }

}
