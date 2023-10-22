package br.com.savebluapi.enums;

public enum UserType {
	CIDADAO("Cidadão"),
	ENFERMEIRO("Enfermeiro"),
	BOMBEIRO("Bobeiro"),
	BRIGADISTA("Brigadista"),
	FISCAL_AMBIENTAL("Fiscal Ambiental"),
	POLICIA_AMBIENTAL("Polícia Ambiental"),
	DEFESA_CIVIL("Defesa Civil"),
	FISCAL_SANITARIO("Fiscal Sanitário");

	private String usertype = "";

    UserType(String usertype) {
        this.usertype = usertype;
    }

    public String getUserType() {
        return  this.usertype;
    }
}

