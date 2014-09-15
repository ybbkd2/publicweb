/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


Ext.define('app.model.system.User', {
    extend: 'Ext.data.Model',
    fields: ['firstName', 'lastName', 'birthDate'],
    proxy: {
        type: 'ajax',
        api: {
            read: 'data/get_user',
            update: 'data/update_user'
        },
        reader: {
            type: 'json',
            root: 'users'
        }
    }
});