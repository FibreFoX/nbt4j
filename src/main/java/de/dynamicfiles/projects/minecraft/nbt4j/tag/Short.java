package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author FibreFoX
 */
public class Short implements Tag {

    private String name = "";
    private short value = 0;

    public static Short readFromStream(DataInputStream inputStream) throws IOException {
        Short shortTag = new Short();
        shortTag.setName(TagReader.getNameOfTag(inputStream, shortTag.getType()));
        readPayload(inputStream, shortTag);
        return shortTag;
    }

    public static void readPayload(DataInputStream inputStream, de.dynamicfiles.projects.minecraft.nbt4j.tag.Short shortTag) throws IOException {
        shortTag.setValue(inputStream.readShort());
    }

    @Override
    public TagType getType() {
        return TagType.SHORT;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

}
