package fr.yazhog.charlotte.utils;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.temporal.TemporalAccessor;
import java.util.List;

public class EmbedBuilder {

    private final net.dv8tion.jda.api.EmbedBuilder embedBuilder;

    public EmbedBuilder() {
        this.embedBuilder = new net.dv8tion.jda.api.EmbedBuilder();
    }

    public StringBuilder getDescriptionBuilder() {
        return embedBuilder.getDescriptionBuilder();
    }

    public List<MessageEmbed.Field> getFields() {
        return embedBuilder.getFields();
    }

    public boolean isEmpty() {
        return embedBuilder.isEmpty();
    }

    public boolean isValidLength() {
        return embedBuilder.isValidLength();
    }

    public EmbedBuilder clearFields() {
        embedBuilder.clearFields();
        return this;
    }

    public EmbedBuilder clear() {
        embedBuilder.clear();
        return this;
    }

    public EmbedBuilder addBlankField(boolean inline) {
        embedBuilder.addBlankField(inline);
        return this;
    }

    public EmbedBuilder addField(MessageEmbed.Field field) {
        embedBuilder.addField(field);
        return this;
    }

    public EmbedBuilder addField(String name, String value, boolean inline) {
        embedBuilder.addField(name, value, inline);
        return this;
    }

    public EmbedBuilder setTitle(String title, String url) {
        embedBuilder.setTitle(title, url);
        return this;
    }

    public EmbedBuilder setTitle(String title) {
        return setTitle(title, null);
    }

    public EmbedBuilder setTimestamp(TemporalAccessor temporalAccessor) {
        embedBuilder.setTimestamp(temporalAccessor);
        return this;
    }

    public EmbedBuilder setAuthor(String name, String url, String iconUrl) {
        embedBuilder.setAuthor(name, url, iconUrl);
        return this;
    }

    public EmbedBuilder setAuthor(String name, String url) {
        return setAuthor(name, url, null);
    }

    public EmbedBuilder setAuthor(String name) {
        return setAuthor(name, null, null);
    }

    public EmbedBuilder setImage(String url) {
        embedBuilder.setImage(url);
        return this;
    }

    public EmbedBuilder setThumbnail(String url) {
        embedBuilder.setThumbnail(url);
        return this;
    }

    public EmbedBuilder setColor(Color color) {
        this.embedBuilder.setColor(color);
        return this;
    }

    public EmbedBuilder setDescription(String description) {
        if (description.length() > 2000)
            description = description.substring(0, 2000) + "...";
        embedBuilder.setDescription(description);
        return this;
    }

    public EmbedBuilder appendDescription(String description) {
        if ((embedBuilder.getDescriptionBuilder().length() + description.length()) > 2000) {
            description = embedBuilder.getDescriptionBuilder().toString() + " " + description;
            description = description.substring(0, 2000) + "...";
        } else description = embedBuilder.getDescriptionBuilder().toString() + " " + description;
        setDescription(description);
        return this;
    }

    public EmbedBuilder setFooter(String footer, String avatarurl) {
        embedBuilder.setFooter(footer, avatarurl);
        return this;
    }

    public EmbedBuilder setFooter(String footer) {
        return setFooter(footer, null);
    }

    public MessageEmbed build() {
        return embedBuilder.build();
    }

}
