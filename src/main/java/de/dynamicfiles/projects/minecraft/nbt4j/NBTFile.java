package de.dynamicfiles.projects.minecraft.nbt4j;

import java.io.InputStream;

/**
 * Minecraft "Named Binary Tag"-File.
 *
 * @author FibreFoX
 */
public interface NBTFile {

    boolean loadFromStream(InputStream stream);
}
