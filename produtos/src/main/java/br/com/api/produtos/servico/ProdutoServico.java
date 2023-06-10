package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {
    
    @Autowired
    private ProdutoRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    //Metodo para listar todos os produtos
    
    public Iterable<ProdutoModelo> listar(){
        return pr.findAll();
    }
    //Método para cadastrar produtos
    public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String acao){
        
        if(pm.getNome().equals("")){
            rm.setMensagem("O nome do produto é obrigatório!");
            return new ResponseEntity<>(rm,HttpStatus.BAD_REQUEST);
        }else if(pm.getMarca().equals("")){
            rm.setMensagem("O nome da  marca  é obrigatório!");
            return new ResponseEntity<>(rm,HttpStatus.BAD_REQUEST);
        }else{
           if(acao.equals("cadastrar")){
            return new ResponseEntity<>(pr.save(pm),HttpStatus.CREATED);
           }else{
            return new ResponseEntity<>(pr.save(pm),HttpStatus.OK);
           }
        }
    }
    // Metodo para remover produtos
    public ResponseEntity<RespostaModelo> remover(long codigo){

        pr.deleteById(codigo);

        rm.setMensagem("O produto foi removido com sucesso!");
        return new ResponseEntity<>(rm, HttpStatus.OK);

    }
}
