package com.carlos.HelpDesk.domain.enums;

public enum Prioridade {
    BAIXA(0, "BAIXA"), MÉDIA(1, "MÉDIA"), ALTA(2, "ALTA");
    
    private Integer codigo;
    private String descricao;

    private Prioridade(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer  getCodigo() { 
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
            if(cod == null){
                return null;
            }
            for(Perfil x : Perfil.values()) {
                if(cod.equals(x.getCodigo())) {
                    return x;
                }
            }
                
            throw new IllegalArgumentException("Prioridade inválido!");
        }  
}
