package de.dynamicfiles.projects.minecraft.nbt4j;

/**
 * Source: http://minecraft.gamepedia.com/NBT_format
 *
 * @author FibreFoX
 */
public enum TagType {

    END(0, "TAG_End", false),
    BYTE(1, "TAG_Byte", true),
    SHORT(2, "TAG_Short", true),
    INT(3, "TAG_Int", true),
    LONG(4, "TAG_Long", true),
    FLOAT(5, "TAG_Float", true),
    DOUBLE(6, "TAG_Double", true),
    BYTEARRAY(7, "TAG_Byte_Array", true),
    STRING(8, "TAG_String", true),
    LIST(9, "TAG_List", true),
    COMPOUND(10, "TAG_Compound", true),
    INTARRAY(11, "TAG_Int_Array", true);

    private final int id;
    private final String name;
    private final boolean hasName;

    private TagType(int id, String name, boolean hasName) {
        this.id = id;
        this.name = name;
        this.hasName = hasName;
    }

    public static TagType getTagTypeByID(int someID) {
        for( TagType tagType : TagType.values() ){
            if( tagType.getID() == someID ){
                return tagType;
            }
        }
        return null;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasName() {
        return hasName;
    }

}
