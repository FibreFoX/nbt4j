package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author FibreFoX
 */
public class Float implements Tag {

    private float value = 0.0f;
    private String name = "";

    public static Float readFromStream(DataInputStream inputStream) throws IOException {
        Float floatTag = new Float();
        floatTag.setName(TagReader.getNameOfTag(inputStream, floatTag.getType()));
        readPayload(inputStream, floatTag);
        return floatTag;
    }

    public static void readPayload(DataInputStream inputStream, de.dynamicfiles.projects.minecraft.nbt4j.tag.Float floatTag) throws IOException {
        floatTag.setValue(inputStream.readFloat());
    }

    @Override
    public TagType getType() {
        return TagType.FLOAT;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}
