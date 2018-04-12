Ext.define('app.view.menu.Show', {
    extend: 'Ext.tree.Panel',
    alias:'widget.menushow',
    
	title : 'Menu',
	rootVisible : false,
	cls : 'examples-list',
	lines : false,
	useArrows : true,
	store : 'Menu',
	autoShow: true,
	
	initComponent: function(){
		this.callParent(arguments);
	}
});
