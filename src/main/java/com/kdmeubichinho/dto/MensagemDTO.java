package com.kdmeubichinho.dto;

import com.kdmeubichinho.dto.generics.ObjectDTO;
import com.kdmeubichinho.entities.Anuncio;
import com.kdmeubichinho.entities.Mensagem;
import com.kdmeubichinho.entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class MensagemDTO implements ObjectDTO {

    private Integer idMensagem;
    private Date dataMensagem;
    private String mensagem;
    private Pessoa idPessoa;
    private Anuncio idAnuncio;

    @Override
    public Mensagem build() {
        return new Mensagem();
    }
}
