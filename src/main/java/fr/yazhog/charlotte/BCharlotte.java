package fr.yazhog.charlotte;

import net.dv8tion.jda.api.JDA;

public abstract class BCharlotte {

    private final Charlotte charlotte;

    protected BCharlotte(Charlotte charlotte) {
        this.charlotte = charlotte;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public JDA getJda() {
        return charlotte.getJda();
    }

    public Charlotte getCharlotte() {
        return charlotte;
    }
}
