package br.ufrpe.treinos_dietas.negocio;


import br.ufrpe.treinos_dietas.dados.RepositorioUsuarios;
import br.ufrpe.treinos_dietas.exceptions.*;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumSexo;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Metrica;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;

import java.time.LocalDate;

public class CadastroUsuarios {
    private RepositorioUsuarios repo;

    public CadastroUsuarios() {
        this.repo = new RepositorioUsuarios();
    }

    public void cadastrarUsuario(String nome, String email, String senha,
                                 String sexo, LocalDate nascimento) throws DataInvalidaException, EmailInvalidoException, SexoInvalidoException {

        boolean emailUtilizado = repo.emailUtilizado(email);
        if(!emailUtilizado){
            if(nascimento.isBefore(LocalDate.now())){
                if(EnumSexo.valueOf(sexo) == EnumSexo.MASCULINO || EnumSexo.valueOf(sexo) == EnumSexo.FEMININO){
                    Usuario novoUser = new Usuario(nome, email, senha, EnumSexo.valueOf(sexo), nascimento);
                    repo.criarUsuario(novoUser);
                } else{
                   throw new SexoInvalidoException();
                }
            } else{
                throw new DataInvalidaException();
            }
        } else{
            throw new EmailInvalidoException();
        }
    }
    public void cadastrarUsuario(Usuario usuario) {
        repo.criarUsuario(usuario);
    }
    public void removerUsuario(String email, String senha) throws FalhaDeAuthException, UsuarioNaoCadastradoException {
        try{
            Usuario user = repo.buscarUsuario(email);
            if(user.getSenha().equals(senha)){
                repo.apagarUsuario(user);
            } else{
                throw new FalhaDeAuthException();
            }
        } catch (UsuarioNaoCadastradoException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    public Usuario lerUsuario(String email) throws UsuarioNaoCadastradoException{
        try{
            Usuario user = repo.buscarUsuario(email);
            System.out.println(user);
            return user;
        } catch (UsuarioNaoCadastradoException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    public void editarEmail(String email, String novoEmail) throws UsuarioNaoCadastradoException, EmailInvalidoException{
        try{
            Usuario user = repo.buscarUsuario(email);
            if(!repo.emailUtilizado(novoEmail)){
                user.setEmail(novoEmail);
            } else{
                throw new EmailInvalidoException();
            }
        } catch (UsuarioNaoCadastradoException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    public void editarSenha(Usuario usuario, String novaSenha) {
            usuario.setSenha(novaSenha);
    }
    public void adicionarMetrica(Usuario usuario, int altura, double peso, LocalDate data) throws DataInvalidaException{
        if(data.isBefore(LocalDate.now())){
            Metrica metrica = new Metrica(altura, peso, data);
            usuario.addMetrica(metrica);
        }else{
            throw new DataInvalidaException();
        }
    }
}
