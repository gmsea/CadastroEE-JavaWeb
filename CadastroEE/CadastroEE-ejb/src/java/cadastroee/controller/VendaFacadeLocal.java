/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cadastroee.controller;

import cadastroee.model.Venda;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author gmato
 */
@Local
public interface VendaFacadeLocal {

    void create(Venda venda);

    void edit(Venda venda);

    void remove(Venda venda);

    Venda find(Object id);

    List<Venda> findAll();

    List<Venda> findRange(int[] range);

    int count();
    
}
