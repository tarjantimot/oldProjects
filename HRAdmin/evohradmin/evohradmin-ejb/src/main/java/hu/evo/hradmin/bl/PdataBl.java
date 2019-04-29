/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl;

import hu.evo.hradmin.dto.PdataDto;
import hu.evo.hradmin.model.Pdata;

/**
 *
 * @author Erdahoy
 */
public interface PdataBl {
  PdataDto convertFromEntity(Pdata pdata);
    
  Pdata convertFromDTO(PdataDto pdata);
    
  PdataDto pdataBuilder(Pdata salary);
}
