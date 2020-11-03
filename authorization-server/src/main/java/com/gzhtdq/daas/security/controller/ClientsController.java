package com.gzhtdq.daas.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping("clients")
public class ClientsController {

    @Autowired
    private JdbcClientDetailsService clientsDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Collection.class, new SplitCollectionEditor(Set.class, ","));
        binder.registerCustomEditor(GrantedAuthority.class, new AuthorityPropertyEditor());
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
    public String showEditForm(@RequestParam(value = "client", required = false) String clientId, Model model) {
        ClientDetails clientDetails;
        if (clientId != null) {
            clientDetails = clientsDetailsService.loadClientByClientId(clientId);
        } else {
            clientDetails = new BaseClientDetails();
        }
        model.addAttribute("clientDetails", clientDetails);
        return "form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
    public String editClient(
            @ModelAttribute BaseClientDetails clientDetails,
            @RequestParam(value = "newClient", required = false) String newClient) {
        if (newClient == null) {
            String pwdEncode = passwordEncoder.encode(clientDetails.getClientSecret());
            clientDetails.setClientSecret(pwdEncode);
            clientsDetailsService.updateClientDetails(clientDetails);
        } else {
            String pwdEncode = passwordEncoder.encode(clientDetails.getClientSecret());
            clientDetails.setClientSecret(pwdEncode);
            clientsDetailsService.addClientDetails(clientDetails);
        }
        if (!clientDetails.getClientSecret().isEmpty()) {
            clientsDetailsService.updateClientSecret(clientDetails.getClientId(), clientDetails.getClientSecret());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "{client.clientId}/delete", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
    public String deleteClient(@ModelAttribute BaseClientDetails ClientDetails, @PathVariable("client.clientId") String id) {
        clientsDetailsService.removeClientDetails(id);
        return "redirect:/";
    }


    class AuthorityPropertyEditor implements PropertyEditor {

        private GrantedAuthority grantedAuthority;

        @Override
        public Object getValue() {
            return grantedAuthority;
        }

        @Override
        public void setValue(Object value) {
            this.grantedAuthority = (GrantedAuthority) value;
        }

        @Override
        public boolean isPaintable() {
            return false;
        }

        @Override
        public void paintValue(Graphics gfx, Rectangle box) {
        }

        @Override
        public String getJavaInitializationString() {
            return null;
        }

        @Override
        public String getAsText() {
            return grantedAuthority.getAuthority();
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (text != null && !text.isEmpty()) {
                this.grantedAuthority = new SimpleGrantedAuthority(text);
            }
        }

        @Override
        public String[] getTags() {
            return new String[0];
        }

        @Override
        public Component getCustomEditor() {
            return null;
        }

        @Override
        public boolean supportsCustomEditor() {
            return false;
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {
        }
    }

    class SplitCollectionEditor extends CustomCollectionEditor {

        private final Class<? extends Collection> collectionType;
        private final String splitRegex;

        public SplitCollectionEditor(Class<? extends Collection> collectionType, String splitRegex) {
            super(collectionType, true);
            this.collectionType = collectionType;
            this.splitRegex = splitRegex;
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (text == null || text.isEmpty()) {
                super.setValue(super.createCollection(this.collectionType, 0));
            } else {
                super.setValue(text.split(splitRegex));
            }
        }
    }
}
