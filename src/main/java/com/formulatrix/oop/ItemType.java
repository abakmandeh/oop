package com.formulatrix.oop;

public enum ItemType {
    JSON(1, "JSON"),
    XML(2, "XML");

    private final int typeCode;
    private final String itemDescription;

    ItemType(int typeCode, String itemDescription) {
        this.typeCode = typeCode;
        this.itemDescription = itemDescription;
    }

    public int value() {
        return this.typeCode;
    }

    public static ItemType from(int value){
        for (ItemType itemType : values()) {
            if(itemType.value()== value){
                return itemType;
            }
        }
        throw new IllegalArgumentException("No item type element of " + value);
    }
}
