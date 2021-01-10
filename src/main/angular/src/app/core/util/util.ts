/**
 * Created by djeison.cassimiro on 09/01/2021
 */
export class Util {

  public static replaceAll(valor: string, oldValue: string, newValue: string): string {
    while (valor.indexOf(oldValue) >= 0) {
      valor = valor.replace(oldValue, newValue);
    }

    return valor;
  }

  public static removerBarrasDuplicadasDaUrl(url:string):string{
    url = Util.replaceAll(url, '//', '/');
    url = url.replace('http:/', 'http://');
    url = url.replace('https:/', 'https://');

    return url;
  }

}
