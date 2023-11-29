
package modelo;

public enum Estado {
    
    ACTIVO(1, "ACTIVO"),
    INACTIVO(0, "INACTIVO");
    
    public static Estado getByKey(Integer key){
        for (Estado e : Estado.values()) {
            if (e.getKey().equals(key)){
                return e;
            }
        }
        return null;
    }
    
    public static Estado getByValue(String value){
        for (Estado e : Estado.values()) {
            if (e.getValue().equals(value)){
                return e;
            }
        }
        return null;
    }
    
    private Integer key;
    private String value;

    private Estado(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
    
}
