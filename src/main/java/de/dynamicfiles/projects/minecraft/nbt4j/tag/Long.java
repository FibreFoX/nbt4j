package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author FibreFoX
 */
public class Long implements Tag {

    private String name = "";
    private long value = 0;

    public static Long readFromStream(DataInputStream inputStream) throws IOException {
        Long longTag = new Long();
        longTag.setName(TagReader.getNameOfTag(inputStream, longTag.getType()));
        readPayload(inputStream, longTag);
        return longTag;
    }

    public static void readPayload(DataInputStream inputStream, de.dynamicfiles.projects.minecraft.nbt4j.tag.Long longTag) throws IOException {
        longTag.setValue(inputStream.readLong());
    }

    @Override
    public TagType getType() {
        return TagType.LONG;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

}
