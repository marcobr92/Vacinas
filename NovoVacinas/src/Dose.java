
public class Dose {

	private int idDose;
	private String data;
	
	public int getIdDose(){
		return idDose;
	}
	
	public void setIdDose(int id){
		this.idDose = id;	
	}
	
	public String getData(){
		return data;
	}	
	
	public void setData( String d){
		this.data = d;
	}
	
	public void registro(String data){
		//realizar registro de data
	}
	
	public void doses(){
		//exibe datas de dose tomadas e numero de controle da dose
	}
	
	public void exclui(int opt){
		//exclui o registro da dose opt
	}
	
	public void modifica(int opt, String data){
		//modifica a data da vacina opt
	}
}
