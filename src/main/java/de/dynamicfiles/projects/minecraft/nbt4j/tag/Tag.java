package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;

/**
 *
 * @author FibreFoX
 */
public interface Tag {

    /**
     * Get the type of the read tag.
     *
     * @return
     */
    TagType getType();

    /**
     * Get the label for this tag (will be null when "End"-tag).
     *
     * @return
     */
    String getName();

    void setName(String newName);
}
