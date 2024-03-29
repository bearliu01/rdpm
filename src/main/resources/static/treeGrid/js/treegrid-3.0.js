
;(function($){
	var defaults = {
		width: "100%",
		align: "left",
		open_icon: "/rdpm/treeGrid/images/tgopen.gif",
		close_icon: "/rdpm/treeGrid/images/tgclose.gif",
		leaf_icon: "/rdpm/treeGrid/images/tgleaf.gif",
		loading_icon: "/rdpm/treeGrid/images/tgleaf.gif",
		id_field: "",
		chk_show: false,
		chk_cascade_select_children: false,
		chk_cascade_select_parent: false,
		chk_only_selected: false,
		columns: [],
		dataurl: "",
		dataset: [],
		show_summary: false
	};

	var TreeGrid = function(_target, options){
		var target = _target;

		var settings = $.extend({}, defaults, options || {});
		
		var _dataPool = {};

		var s = "";

		var _container = null;

		var open_icon = settings.open_icon;
		var close_icon = settings.close_icon;
		var leaf_icon = settings.leaf_icon;
		var loading_icon = settings.loading_icon;
		var id_field = settings.id_field || "";
		var chk_show = settings.chk_show || false;
		var chk_cascade_select_children = settings.chk_cascade_select_children || false;
		var chk_cascade_select_parent = settings.chk_cascade_select_parent || false;
		var chk_only_selected = settings.chk_only_selected || false;
		
		var _cols = settings.columns;
		
		var fieldSum = new Array(_cols.length);
		for(var i=0; i<fieldSum.length; i++){
			fieldSum[i] = 0;
		}
		
		var dataurl = settings.dataurl || "";
		var dynamic_load_data = (dataurl != "") ? true : false;
		
		var dataset = settings.dataset || [];
		if(dynamic_load_data){
			dataset = [];
		}
		
		var menus = settings.menus || [];
		
		var show_summary = settings.show_summary || false;
		
		drowHeader = function(){
			s += "<tr class='header'>";
			s += "<td width='46' align='center'></td>";

			if(chk_show){
				s += "<td width='46' align='center'></td>";
			}

			for(var i=0; i<_cols.length; i++){
				var col = _cols[i];
				s += "<td align='center' width='" + (col.width || "") + "'>" + (col.title || "") + "</td>";
			}
			s += "</tr>";
		}
		
		drowData = function(){
			s += drowRowData(dataset, 1, "");
		}
		
		drowRowData = function(_rows, _level, _pid){
			var str = "";
			for(var i=0; i<_rows.length; i++){
				var trid = _pid + "_" + i;
				var ptrid = ((_pid=="")?"":("r"+_pid)); 

				var row = _rows[i];

				var dispaly = "none";
				if(_level<=1) dispaly = ""; 
				
				str += "<tr height='46' id='r" + trid + "' pid='" + ptrid + "' rowindex='' rowlevel='" + _level + "' style='display:" + dispaly + "'>";
				
				_dataPool["r" + trid] = row; 

				str += "<td align='center' rowindex=''>&nbsp;</td>";

				var idValue = "";
				if(id_field != ""){
					idValue = row[id_field] || "";
				}
				
				if(chk_show){
					str += "<td align='center'><input type='checkbox' trid='r" + trid + "' value='" + idValue + "'></td>";
				}

				for(var j=0; j<_cols.length; j++){
					var col = _cols[j];
					str += "<td align='" + (col.align || settings.align || "left") + "'";

					if(j==0){
						str += " style='text-indent:" + (20 * (_level-1)) + "px;'> ";
					}else{
						str += ">";
					}

					if(j==0){
						if(dynamic_load_data){
							str += "<img folder='Y' loaded='N' idvalue='" + idValue + "' trid='r" + trid + "' src='" + close_icon + "' class='image_hand'>";
						}else{
							if(row.children && row.children.length > 0){
								str += "<img folder='Y' trid='r" + trid + "' src='" + close_icon + "' class='image_hand'>";
							}else{
								str += "<img src='" + leaf_icon + "' class='image_nohand'>";
							}
						}
					}
					
					var cellValue = row[col.field] || col.defaultValue || "";
					if(col.handler){
						str += (eval(col.handler + ".call(new Object(), 'r'+trid, cellValue, row, col)") || "") + "</td>";
					}else{
						if(j==0){
							str += cellValue + "<font color=red class='subcount'></font></td>";
						}else{
							str += cellValue + "</td>";
						}
					}
				}
				str += "</tr>";

				if(!dynamic_load_data && row.children){
					str += drowRowData(row.children, _level+1, trid);
				}
			}
			return str;
		}
		
		loadRootNodeData = function(){
			jQuery.ajax({
				url: dataurl,
				type: "post",
				dataType: "json",
				async: false, 
				success: function(ds){
					if(ds && ds.length > 0){
						for(var i=0; i<ds.length; i++){
							dataset.push(ds[i]);
						}
						
						var str = drowRowData(ds, 1, "");
						$(str).appendTo(_container.find("table.TreeGrid"));
					}
				},
				error: function(req, msg, ex){
					alert(msg + ": " + ex);
				}
			});
		}

		showSubRows = function(_trid){
			var trs = _container.find("tr[pid='" + _trid + "']");
			trs.css("display", "");

			for(var i=0; i<trs.length; i++){
				var _src = _container.find("img[trid='" + $(trs[i]).attr("id") + "']").prop("src");
				if(_src != null && _src.indexOf(open_icon) >= 0){
					showSubRows($(trs[i]).attr("id"));
				}
			}
		}

		showHiddenRow = function(_trid, _open, partShow){
			if(_open){ 
				if(partShow){
					_container.find("img[trid='" + _trid + "']").prop("src", open_icon);
					showSubRows(_trid);
				}else{
					_container.find("img[trid='" + _trid + "']").prop("src", open_icon);
					_container.find("img[trid^='" + _trid + "_']").prop("src", open_icon);
					_container.find("tr[id^=" + _trid + "_]").css("display", "");
				}
			}else{
				_container.find("img[trid='" + _trid + "']").prop("src", close_icon); 
				_container.find("tr[id^=" + _trid + "_]").css("display", "none");
			}
		}

		loadSubNodeData = function(_img, _idValue, _tr, _trid, _rowLevel){
			$(_img).prop("src", loading_icon);
			jQuery.ajax({
				url: dataurl + "?parent_id=" + _idValue,
				type: "post",
				dataType: "json",
				async: false, 
				success: function(ds){
					if(ds && ds.length > 0){
						for(var i=0; i<ds.length; i++){
							dataset.push(ds[i]);
						}
						
						var str = drowRowData(ds, parseInt(_rowLevel)+1, _trid.replace("r", ""));
						$(str).insertAfter(_tr);
						
						_container.find("tr[pid='" + _trid + "']").css("display", "");
						
						$(_img).prop("src", open_icon);
						
						showAfter(false);
						
					}else{
						$(_img).prop("src", leaf_icon);
						
						$(_img).removeAttr("trid");
						$(_img).removeAttr("folder");
						$(_img).removeAttr("loaded");
						$(_img).removeAttr("idvalue");
						$(_img).attr("class", "image_nohand");
					}
					
					$(_img).attr("loaded", "Y");
				},
				error: function(req, msg, ex){
					$(_img).prop("src", close_icon);
					alert(msg + ": " + ex);
				}
			});
		}

		init = function(){
			_container.find("tr[pid]").hover(
				function(){
					$(this).addClass("row_hover");
				},
				function(){
					$(this).removeClass("row_hover");
				}
			);
			
			_container.find("td.menuItem").hover(
				function(){
					$(this).css("background-color", "#FFF");
				},
				function(){
					$(this).css("background-color", "#DFDFDF");
				}
			);

			_container.on("mousedown", "tr[pid]", function(e){
				var _event = e || window.event; 

				_container.find("#contextMenu").hide();
				
				$(this).siblings(".row_active").removeClass("row_active");
				$(this).addClass("row_active");

				if(1 == _event.which){ 
					if(jQuery.isFunction(settings.onClickRow)){
						var s_trid = $(this).attr("id") || "";
						var s_index = $(this).attr("rowindex") || "";
						var s_data = _dataPool[s_trid] || {}; 
						settings.onClickRow(s_trid, s_index, s_data);
					}
				}else if(3 == _event.which){ 
					if(menus && menus.length > 0){
						var trid = $(this).attr("id") || "";
						_container.find("td.menuItem").attr("trid", trid);

						var menu = _container.find("#contextMenu");
						$(menu).css("left", _event.pageX);
						$(menu).css("top", _event.pageY);
						$(menu).css("display", "");
					}
				}
			});

			_container.on("click", "td.menuItem > a", function(){
				var url = $(this).parents("td[trid]:first").attr("url") || "";
				var trid = $(this).parents("td[trid]:first").attr("trid") || "";
				
				if(url && url!="" && trid && trid!=""){
					var rowdata = _dataPool[trid];
					if(rowdata){
						for(var key in rowdata){
							url = url.replace("#"+key+"#", rowdata[key]||"");
						}
						$(this).prop("href", url);
					}
				}
			});
			
			_container.on("dblclick", "tr[pid]", function(){
				if(jQuery.isFunction(settings.onDblClickRow)){
					var s_trid = $(this).attr("id") || "";
					var s_index = $(this).attr("rowindex") || "";
					var s_data = _dataPool[s_trid] || {}; 
					settings.onDblClickRow(s_trid, s_index, s_data);
				}
			});

			_container.on("click", "img[folder]", function(){
				var imgObj = this;
				var trid = $(imgObj).attr("trid"); 
				var loaded = $(imgObj).attr("loaded") || "";
				var idValue = $(imgObj).attr("idvalue") || "";
				
				var tr = $(imgObj).parents("tr");
				var rowLevel = tr.attr("rowlevel");
				
				var _open = false;
				if($(imgObj).prop("src").indexOf(close_icon) >= 0){
					_open = true;
				}else{
					_open = false;
				}

				if(dynamic_load_data){
					if(_open){
						if(loaded == "N"){
							loadSubNodeData(imgObj, idValue, tr, trid, rowLevel);
						}else{
							showHiddenRow(trid, _open, true);
						}
					}else{
						showHiddenRow(trid, _open, true);
					}
				}else{
					showHiddenRow(trid, _open, true);
				}
			});

			_container.on("click", "input[trid]:checkbox", function(){
				var o = $(this);
				var trid = o.attr("trid");
				var isChecked = o.is(":checked");

				if(chk_only_selected){
					_container.find("input[trid!='"+trid+"']:checkbox:checked").prop("checked", false);
				}else{
					if(isChecked){
						if(chk_cascade_select_parent){
							var pid = $(o).parents("tr").attr("pid");
							while(pid != null && pid.length > 0){
								o = _container.find("input[trid='"+pid+"']:checkbox");
								o.prop("checked", true);
								pid = $(o).parents("tr").attr("pid");
							}
						}
	
						if(chk_cascade_select_children){
							_container.find("input[trid^='" + trid + "_']:checkbox").prop("checked", true);
						}
					}else{
						if(chk_cascade_select_children){
							_container.find("input[trid^='" + trid + "_']:checkbox:checked").prop("checked", false);
						}
					}
				}
			});
		}

		calculateCount = function (){
			$.each(_container.find("tr[pid='']"), function(i, o){
				var pid = $(o).attr("id");
				var children = _container.find("tr[id^='" + pid + "_']"); 
				if(children && children.length > 0){
					$(o).find("font.subcount").html(" (" + children.length + ")");
					calculateSubCount(pid);
				}
			});
		}

		calculateSubCount = function(pid){
			$.each(_container.find("img[folder][trid^='" + pid + "_']"), function(i, o){
				var tr = $(o).parents("tr:first");
				var trid = tr.attr("id");
				var children = _container.find("tr[id^='" + trid + "_']");
				if(children && children.length > 0){
					tr.find("font.subcount").html(" (" + children.length + ")");
					calculateSubCount(trid);
				}
			});
		}
		
		fieldCalculation = function(){
			var colspan;
			if(chk_show){
				colspan = 3;
			}else{
				colspan = 2;
			}
			
			$("#summary").remove();
			
			for(var i=0; i<fieldSum.length; i++){
				fieldSum[i] = 0;
			}
			
			var totalRows = _container.find("tr[pid]").length || 0;
			
			var _str = "<tr class='summary' id='summary'>";
			_str += "<td align='center' colspan='" + colspan + "'>合计：</td>";
			
			for(var i=1; i<_cols.length; i++){
				var col = _cols[i];
				var fieldCal = col.fieldCal || false;
				var calStyle = col.calStyle || "sum"; 
				
				if(fieldCal){
					calculateValues(dataset, col, i, calStyle, true);
					
					if(calStyle == "avg" && totalRows > 0){
						var digit = col.digit || 2;
						if(digit < 0) digit = 0;
						
						fieldSum[i] = (fieldSum[i] / totalRows).toFixed(digit);
					}
				}
			}
			
			for(var i=1; i<_cols.length; i++){
				var col = _cols[i];
				var fieldCal = col.fieldCal || false;
				var calStyle = col.calStyle || "sum"; 
				
				var _title = "求和";
				if(calStyle == "min") {
					_title = "最小值";
				}else if(calStyle == "max"){
					_title = "最大值";
				}else if(calStyle == "avg"){
					_title = "平均值";
				}
				
				if(fieldCal){
					_str += "<td align='center' title='" + _title + "'>" + fieldSum[i] + "</td>";
				}else{
					_str += "<td align='center'>&nbsp;</td>";
				}
			}
			
			_str += "</tr>";
			$(_str).appendTo(_container.find("table.TreeGrid"));
		}
		
		calculateValues = function(_dataset, _col, _colIndex, _calStyle, _first){
			for(var j=0; j<_dataset.length; j++){
				var rowdata = _dataset[j];
				var fieldValue = rowdata[_col.field] || 0;
				
				if(_calStyle == "sum"){
					fieldSum[_colIndex] += fieldValue;
				}else if(_calStyle == "min"){
					if(_first){
						fieldSum[_colIndex] = fieldValue;
						_first = false;
					}else{
						if(fieldValue < fieldSum[_colIndex]){
							fieldSum[_colIndex] = fieldValue;
						}
					}
				}else if(_calStyle == "max"){
					if(_first){
						fieldSum[_colIndex] = fieldValue;
						_first = false;
					}else{
						if(fieldValue > fieldSum[_colIndex]){
							fieldSum[_colIndex] = fieldValue;
						}
					}
				}else if(_calStyle == "avg"){
					fieldSum[_colIndex] += fieldValue;
				}
				
				if(rowdata.children && rowdata.children.length > 0){
					calculateValues(rowdata.children, _col, _colIndex, _calStyle, _first);
				}
			}
		}

		calculateRowIndex = function(){
			$.each(_container.find("tr[pid]"), function(i, o){
				$(o).attr("rowindex", (i+1));
				$(o).find("td[rowindex]").text(i+1);
			});
		}

		showAfter = function(needInit){
			if(needInit){
				init();
			}
			
			calculateCount();
			calculateRowIndex();
			
			if(show_summary){
				fieldCalculation();
			}
		}

		refresh = function(){
			if(_container != null){
				_container.off("mousedown", "tr[pid]");
				_container.off("click", "td.menuItem > a");
				_container.off("dblclick", "tr[pid]");
				_container.off("click", "img[folder]");
				_container.off("click", "input[trid]:checkbox");
			}
			
			$(target).empty();

			_dataPool = {};

			s = "";
			_container = null;
		
			for(var i=0; i<fieldSum.length; i++){
				fieldSum[i] = 0;
			}

			if(dynamic_load_data){
				dataset = [];
			}
		}

		this.show = function(){
			refresh();

			if(menus && menus.length > 0){
				s += "<table id='contextMenu' class='contextMenu' style='display:none;'>";
				for(var i=0; i<menus.length; i++){
					var _menu = menus[i];
					s += "<tr><td class='menuItem' trid=\"\" url=\"" + (_menu.url||"javascript:void(0);") + "\"><a href=\"" + (_menu.url||"javascript:void(0);") + "\" target='"+(_menu.target||"_blank") + "'>" + (_menu.title||"") + "</a></td></tr>";
				}
				s += "</table><br>";
			}
			
			s += "<table cellspacing=0 cellpadding=0 width='" + (settings.width || "100%") + "' class='TreeGrid'>";
			drowHeader();
			
			if(!dynamic_load_data){
				drowData();
			}
			
			s += "</table>";
			
			_container = $(target);
			_container.empty().append(s);
			
			if(dynamic_load_data){
				loadRootNodeData();
			}

			showAfter(true);
		}

		this.expandAll = function(){
			var trs = _container.find("tr[pid='']");
			for(var i=0; i<trs.length; i++){
				var trid = $(trs[i]).attr("id");
				showHiddenRow(trid, true, false);
			}
		}

		this.collapseAll = function(){
			var trs = _container.find("tr[pid='']");
			for(var i=0; i<trs.length; i++){
				var trid = $(trs[i]).attr("id");
				showHiddenRow(trid, false, false);
			}
		}

		this.getDataPool = function(){
			return _dataPool;
		}
		
		this.getDataset = function(){
			return dataset;
		}

		this.getRowData = function(trid){
			return _dataPool[trid];
		}
		
		this.getSelectedRowLevel = function(){
			var _rowLevel = "";
			if(chk_show && chk_only_selected) {
				var itemsArr = this.getSelections();
				if(itemsArr && itemsArr.length == 1){
					_rowLevel = itemsArr[0].level;
				}
			}
			return _rowLevel;
		}

		this.version = function(){
			return "TreeGrid-3.0";
		}
	
		this.getSelected = function(){
			if(chk_show && chk_only_selected) {
				var itemsArr = this.getSelections();
				if(itemsArr && itemsArr.length == 1){
					return itemsArr[0];
				}
			}
			return null;
		}
		
		this.getParent = function(){
			if(chk_show && chk_only_selected) {
				var itemsArr = this.getSelections();
				if(itemsArr && itemsArr.length == 1){
					var trid = _container.find("#" + itemsArr[0].id).attr("pid") || "";
					if(trid && trid.length > 0){
						var cPid = _container.find("#" + trid).attr("pid") || "";
						var cRowIndex = _container.find("#" + trid).attr("rowindex") || "";
						var cRowLevel = _container.find("#" + trid).attr("rowlevel") || "";
						var cData = _dataPool[trid]; 
						return new TreeGridItem(_container, this, trid, cPid, cRowIndex, cRowLevel, cData);
					}
				}
			}
			return null;
		}
		
		this.getChildren = function(){
			var arr = [];
			if(chk_show && chk_only_selected) {
				var itemsArr = this.getSelections();
				if(itemsArr && itemsArr.length == 1){
					$.each(_container.find("tr[pid='" + itemsArr[0].id + "']"), function(i, o){
						var cId = $(o).attr("id") || "";
						var cPid = $(o).attr("pid") || "";
						var cRowIndex = $(o).attr("rowindex") || "";
						var cRowLevel = $(o).attr("rowlevel") || "";
						var cData = _dataPool[cId] || {}; 
						arr.push(new TreeGridItem(_container, this, cId, cPid, cRowIndex, cRowLevel, cData));
					});
				}
			}
			return arr;
		}
		
		this.getSelections = function(){
			var itemsArr = new Array();
			if(chk_show) {
				$.each(_container.find("input[trid]:checkbox:checked").parents("tr"), function(i, o){
					var sId = $(o).attr("id") || "";
					var sPid = $(o).attr("pid") || "";
					var sRowIndex = $(o).attr("rowindex") || "";
					var sRowLevel = $(o).attr("rowlevel") || "";
					var sData = _dataPool[sId] || {}; 
					var item = new TreeGridItem(_container, this, sId, sPid, sRowIndex, sRowLevel, sData);
					itemsArr.push(item);
				});
			}
			return itemsArr;
		}

		this.getSelectedCheckboxValues = function(){
			if(chk_show){
				var checkboxValues = new Array();
				$.each(_container.find("input[trid]:checkbox:checked"), function(i, o){
					var value = $(o).val() || "";
					if(value != ""){
						checkboxValues.push(value);
					}
				});
				return checkboxValues.join(",");
			}else{
				return "";
			}
		}

		this.setDataset = function(ds){
			if(!dynamic_load_data){
				var _ds = ds || [];
				settings.dataset = _ds;
				dataset = _ds;
			}
		}
	};

	$.fn.showTreeGrid = function(options){
		var treegrid = new TreeGrid(this, options);
		treegrid.show();
		return treegrid;
	};
})(jQuery);

var TreeGridItem = function(_container, _treeGrid, _rowId, _rowParentId, _rowIndex, _rowLevel, _rowData){
	this.container = _container;
	this.treeGrid = _treeGrid;
	this.id = _rowId;
	this.pid = _rowParentId;
	this.index = _rowIndex;
	this.level = _rowLevel;
	this.data = _rowData;
};

function clickIE4(){
    if (event.button==2){
    	hideContextMenu();
    	return false;
    }
}

function clickNS4(e){
    if (document.layers||document.getElementById&&!document.all){
        if (e.which==2||e.which==3){
        	hideContextMenu();
        	return false;
        }
    }
}

function OnDeny(){
    if(event.ctrlKey || event.keyCode==78 && event.ctrlKey || event.altKey || event.altKey && event.keyCode==115){
    	return false;
    }
}

if (document.layers){
    document.captureEvents(Event.MOUSEDOWN);
    document.onmousedown = clickNS4;
    //document.onkeydown = OnDeny();
}else if (document.all&&!document.getElementById){
    document.onmousedown = clickIE4;
    //document.onkeydown = OnDeny();
}

document.oncontextmenu = new Function("return false");

document.onclick = function(){
	hideContextMenu();
}

function hideContextMenu(){
	setTimeout(function(){jQuery("table.contextMenu").hide()}, 50);
}