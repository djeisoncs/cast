/**
 * Created by djeison.cassimiro on 09/01/2021
 */
export const constantes = {
  contexto: {
    dev: {
      baseUrl: 'http://localhost:8080',
      api: 'http://localhost:8080/api',
    }
  },

  paths: {
    academico: {
      inicio: {
        basePath: 'academico/inicio',
      },
      categoria: {
        basePath: 'academico/categoria',
        listar: 'academico/categoria/listar',
        cadastrar: 'academico/categoria/cadastrar',
        editar: 'academico/categoria/editar/:id',
        consultar: 'academico/categoria/consultar/:id'
      },
      curso: {
        basePath: 'academico/curso',
        listar: 'academico/curso/listar',
        cadastrar: 'academico/curso/cadastrar',
        editar: 'academico/curso/editar/:id',
        consultar: 'academico/curso/consultar/:id'
      }
    },
  },

  classes: {
    status: 'br.com.cast.avaliacao.model.Status'
  },

  icones: {
    confirmacao: 'far fa-question-circle'
  }
};
