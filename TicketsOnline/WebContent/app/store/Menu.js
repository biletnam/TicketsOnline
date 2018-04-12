Ext.define('app.store.Menu', {
    extend: 'Ext.data.TreeStore',

    root: {
        expanded: true,
        children: [
            {
                text: 'Venta',
                expanded: true,
                children: [
                    { leaf: true, text: 'Global', widget: 'saleglobalshow'},
                ]
            }
        ]
    }
});
