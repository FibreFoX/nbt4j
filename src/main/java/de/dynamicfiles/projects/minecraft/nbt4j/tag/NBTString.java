package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 *
 * @author FibreFoX
 */
public class NBTString implements Tag {

    private static final Charset STRING_CHARSET = Charset.forName("UTF-8");

    private String name = "";
    private String value = "";

    public static NBTString readFromStream(DataInputStream inputStream) throws IOException {
        NBTString stringTag = new NBTString();
        stringTag.setName(TagReader.getNameOfTag(inputStream, stringTag.getType()));
        readPayload(inputStream, stringTag);
        return stringTag;
    }

    public static void readPayload(DataInputStream inputStream, NBTString stringTag) throws IOException {
        // first two bytes specify the length of the payload
        short valueLength = inputStream.readShort();
        // payload is stored as UTF-8
        byte[] payload = new byte[valueLength];
        inputStream.readFully(payload, 0, valueLength);

        // put it into value
        stringTag.setValue(new String(payload, STRING_CHARSET));
    }

    @Override
    public TagType getType() {
        return TagType.STRING;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
