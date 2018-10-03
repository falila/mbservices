package com.falisoft.api.service;

import com.falisoft.api.repository.ClientRepository;
import com.falisoft.entity.Client;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (9 2018)
 * @author Raphael KEITA
 * 
 */
@Service
public class ClientImpl implements IClient{

    @Autowired
    private ClientRepository cRepository;

    
    @Override
    @Transactional
    public Client findByReference(String ref){
        
        Client client = null;
        try{
            client = cRepository.findByReference(ref);
        }catch(Exception e){
            
        }finally{
            return client;
        }  
    }

    @Override
    public void save(Client client) {
        cRepository.save(client);
    }

    @Override
    public Client update(Client cli) {
        return cRepository.saveAndFlush(cli);
    }
}
