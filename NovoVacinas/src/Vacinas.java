import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Vacinas {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
				
		int opt = 0;
		Usuario user1 = new Usuario();
		Dose dose = new Dose();
		String nome;
		String cpf;
		String senha;
		int id;
		String data;
		String senhasql;
		Scanner ler = new Scanner(System.in);
		
		senhasql = " ";
		
		System.out.println("*------------------------------------------------------------------------------------*");
		System.out.println("Bem vindo ao Vacinas - Controle de gripe");
		System.out.println("Esse sistema tem a finalidade de cadastrar e gerenciar vacinas de gripe de usuarios");
		System.out.println("Desenvolvido por Marco Beraldi e Thiago Mendes");
		System.out.println("Mais informações em http://1drv.ms/1LHgiV6, Acesse!");
		System.out.println("*------------------------------------------------------------------------------------*");
		System.out.println("\n\n\n");
		
						
		do{
			
			System.out.println("Selecione a opção desejada:");
			System.out.println("1-Cadastro de novo usuario");
			System.out.println("2-Acesso de usuario");
			System.out.println("9-Sair");
			opt = ler.nextInt();
			
			switch(opt){
			case 1://cadastro
				System.out.println("Cadastro de novo usuario no sistema");
				System.out.println("Informe seu primeiro nome");
				nome = ler.next();
				user1.setUserName(nome);
							
				System.out.println("Informe seu cpf (Esse sera seu usuario)");
				cpf = ler.next();
				user1.setCpf(cpf);

				System.out.println("Informe sua senha");
				senha = ler.next();
				user1.setUserSenha(senha);
				
				
				//[C]RUD
				try{
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection conn = DriverManager.getConnection("jdbc:sqlserver://dbdevopsband.cloudapp.net;DatabaseName=vacinas","sa", "!@#Bandtec");
					Statement stmt = conn.createStatement();				
					
					PreparedStatement st = conn.prepareStatement("insert into usuario values (?,?,?)");
					//nome, cpf e senha, o usuario será o cpf
		            st.setString(1, user1.getUserName());
		            st.setString(2, user1.getCpf());
		            st.setString(3, user1.getUserSenha());
		            
		            st.execute();
		            st.close();
					conn.close();					
					}
				catch(Exception e){e.printStackTrace();}
				
				System.out.println("Cadatro efetuado!");
				System.out.println("Voltando ao menu anterior \n\n");
			break;
			
			case 2://acesso ao sistema
				//C[R]UD
				
				System.out.println("Acesso de usuario");
				System.out.println("Informe seu CPF:");
				cpf = ler.next();
				System.out.println("Informe sua senha:");
				senha = ler.next();
				
				try	{
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection conn = DriverManager.getConnection("jdbc:sqlserver://dbdevopsband.cloudapp.net;DatabaseName=vacinas","sa", "!@#Bandtec");
					//SELECT
					Statement stmt = conn.createStatement();
					String teste;
					teste = " ";
					ResultSet rset = stmt.executeQuery("select senha from usuario where cpf='"+cpf+"';");
					while (rset.next())
					{
						teste = (rset.getString("senha"));
					}
					senhasql = teste;
					rset.close();
					stmt.close();
					conn.close();
				}
				catch(Exception e){e.printStackTrace();}
				
				if(senha.equals(senhasql)){
					System.out.println("Acesso liberado!");
					do{
						System.out.println("Selecione a opção desejada");
						System.out.println("1-Registro da data de uma dose tomada");
						System.out.println("2-Exclusão da data de uma dose");
						System.out.println("3-Modificação de uma dose");
						System.out.println("4-Exibir vacinas registradas");
						System.out.println("0-Voltar ao menu anterior");
						opt = ler.nextInt();
						
						switch(opt){
						case 1://registro
							//[C]RUD
							System.out.println("Registro da data de uma dose");
							System.out.println("Informe a data da dose tomada para cadastro");
							data = ler.next();
							
							try{
								Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
								Connection conn = DriverManager.getConnection("jdbc:sqlserver://dbdevopsband.cloudapp.net;DatabaseName=vacinas","sa", "!@#Bandtec");
								Statement stmt = conn.createStatement();				
								
								PreparedStatement st = conn.prepareStatement("insert into dose values (?,?)");
					            st.setString(1, data);
					            st.setString(2, cpf);
					            
					            st.execute();
					            st.close();
								conn.close();					
								}
							catch(Exception e){e.printStackTrace();}
							
							System.out.println("Data registrada!\n\n");
						break;
						
						case 2://exclusão
							//CRU[D]
							System.out.println("Exclusão da data de uma dose");
							System.out.println("Informe a dose de exclusão ou 0 para cancelar");
							opt = ler.nextInt();
							if(opt!=0){

								try{
									Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
									Connection conn = DriverManager.getConnection("jdbc:sqlserver://dbdevopsband.cloudapp.nett;DatabaseName=vacinas","sa", "!@#Bandtec");
									Statement stmt = conn.createStatement();				
																		
									PreparedStatement st = conn.prepareStatement("delete from dose where idDose = (?);");
						            st.setInt(1, opt);
						            
						            st.execute();
						            st.close();
									conn.close();					
									}
								catch(Exception e){e.printStackTrace();}
								System.out.println("Dose excluida com sucesso! \n\n");
							}
							opt = 5;
						break;
						
						case 3://modificar
							//CR[U]D
							System.out.println("Modificação de uma dose");
							System.out.println("Informe o codigo da dose para modificar");
							opt = ler.nextInt();
							System.out.println("Informe a data correta");
							data = ler.next();

							try{
								Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
								Connection conn = DriverManager.getConnection("jdbc:sqlserver://dbdevopsband.cloudapp.net;DatabaseName=vacinas","sa", "!@#Bandtec");
								Statement stmt = conn.createStatement();				
																	
								PreparedStatement st = conn.prepareStatement("UPDATE dose SET data=(?) where idDose = (?);");
					            st.setString(1, data);
					            st.setInt(2, opt);
					            
					            st.execute();
					            st.close();
								conn.close();					
								}
							catch(Exception e){e.printStackTrace();}
							
							System.out.println("Data modificada!");
							
						break;
						
						case 4://exibir historico de vacinas
							//C[R]UD
							if(opt==4){
								System.out.println("Exibir vacinas registradas");
								
								try{
									Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
									Connection conn = DriverManager.getConnection("jdbc:sqlserver://dbdevopsband.cloudapp.net;DatabaseName=vacinas","sa", "!@#Bandtec");
									Statement stmt = conn.createStatement();
									String teste;
									String tudo;
									tudo  = " ";
									teste = " ";
									ResultSet rset = stmt.executeQuery("select * from dose where cpf='"+ cpf +"';");
									while (rset.next())
									{
										teste = ("Codigo de identificação: " + rset.getInt("idDose") + " Data: " + rset.getString("data") + "\n" );
										tudo = tudo + teste;
									}
									System.out.println(tudo);
									rset.close();
									stmt.close();
									conn.close();
									
								}
								catch(Exception e){e.printStackTrace();}
									
								System.out.println("Voltando ao menu principal \n\n\n");
							}
						break;
						
						case 5://faz nada
						break;
						
						case 0://voltar
							System.out.println("voltando ao menu anterior");
						break;
						default: System.out.println("Opção invalida!");
						}
					}while(opt!=0);
					
				}else{
					System.out.println("Falha de acesso!");
				}
			break;
			
			case 9://finaliza
				System.out.println("Finalizando o programa");
			break;
			
			default: System.out.println("Opção invalida!");
			}
		}while(opt!=9);		
		
	}
}

