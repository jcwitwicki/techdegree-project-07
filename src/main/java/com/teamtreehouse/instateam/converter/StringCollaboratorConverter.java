package com.teamtreehouse.instateam.converter;

import com.teamtreehouse.instateam.dao.CollaboratorDao;
import com.teamtreehouse.instateam.model.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StringCollaboratorConverter implements Converter<String, Collaborator> {

        @Autowired
        private CollaboratorDao collaboratorDao;

        @Override
        public Collaborator convert(String source) {
            Collaborator collaborator = collaboratorDao.findById(new Long(source));
            return collaborator;
        }

        public ConversionService getConversionService() {
            ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
            Set<Converter> converters = new HashSet<>();
            converters.add(new com.teamtreehouse.instateam.converter.StringRoleConverter());
            bean.setConverters(converters);
            return bean.getObject();
        }
    }

