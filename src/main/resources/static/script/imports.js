var base_url = location.href.substring(0, location.href.lastIndexOf("/"));
let arr = base_url.split("/");

if (arr[3] === 'controle-ativos') {
	base_url = `${arr[0]}//${arr[2]}/${arr[3]}/api`
} else {
	base_url = `${arr[0]}//${arr[2]}/api`
}
