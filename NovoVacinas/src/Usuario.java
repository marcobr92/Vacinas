
public class Usuario {

	private  int idUser;
	private String userName;
	private String cpf;
	private String userSenha;
	
	
	public int getIdUser(){
		return idUser;
	}
	
	public void setIdUser(int id){
		this.idUser = id;		
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String name){
		this.userName = name;
	}
	
	public String getCpf(){
		return cpf;
	}
	
	public void setCpf(String cpf){
		this.cpf = cpf;
	}
	
	public String getUserSenha(){
		return userSenha;
	}
	
	public void setUserSenha(String s){
		this.userSenha = s;
	}
	
	public int login(int id, String senha){
		
		return 1;
	}
	
}

