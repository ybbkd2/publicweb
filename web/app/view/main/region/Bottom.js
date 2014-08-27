/**
 * 系统主页的底部区域，主要放置用户单位信息，服务单位和服务人员信息
 */
Ext.define('app.view.main.region.Bottom', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.mainbottom',
    uses: ['app.ux.ButtonTransparent'],
    defaults: {
        xtype: 'buttontransparent'
    },
    style: 'background-color : #f6f5ec;',
    items: [ {
            bind: {
                text: '用户:{user.name}'
            },
            glyph: 0xf007
        },{
            bind: {
                text: 'IP:{user.company}'
            },
            glyph: 0xf0f7
        },
        '->', {
            bind: {
                text: '©{service.copyright}'
            }
        }]
});