package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author FibreFoX
 */
public class End implements Tag {

    public static End readFromStream(DataInputStream inputStream) throws IOException {
        return new End();
    }

    @Override
    public TagType getType() {
        return TagType.END;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String newName) {
        // NO-OP
    }
}
