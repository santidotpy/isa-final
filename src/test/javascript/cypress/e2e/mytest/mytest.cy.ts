import { passwordLoginSelector, submitLoginSelector, usernameLoginSelector } from '../../support/commands';

import {
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('Limpiar SessionStorage', () => {
  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });
    cy.visit('');
  });

  describe('Login Incorrecto', () => {
    before(() => {
      cy.visit('');
    });

    it('debe aparecer un cartel por las credenciales incorrectas', () => {
      cy.clickOnLoginItem();
      cy.get(usernameLoginSelector).click().type('admin');
      cy.get(passwordLoginSelector).type('pepe');
      cy.get(submitLoginSelector).click();
      cy.contains('Failed to sign in! Please check your credentials and try again.').should('be.visible');
    });
  });

  describe('iniciar sesion y probar funcionalidades', () => {
    //Antes de cada prueba dentro del conjunto (describe), intercepta cualquier solicitud POST a '/api/account' y le asigna
    // un alias llamado 'createRequest'. Esto se hace para poder esperar a esta solicitud más adelante y verificar su respuesta.
    beforeEach(() => {
      cy.intercept('POST', '/api/authors').as('createRequest');
    });

    beforeEach(() => {
      cy.intercept('DELETE', '/api/authors/*').as('deleteRequest');
    });

    beforeEach(() => {
      cy.login('admin', 'admin');
      cy.visit('');
    });

    it('crear autor', () => {
      cy.clickOnEntityMenuItem('author');
      cy.get(entityCreateButtonSelector).click();
      cy.get(`[data-cy="firstName"]`).type('santiago').should('have.value', 'santiago');

      cy.get(`[data-cy="lastName"]`).type('Graffigna').should('have.value', 'Graffigna');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@createRequest').then(({ response }) => expect(response!.statusCode).to.equal(201));
    });

    it('eliminar ultimo autor', () => {
      cy.clickOnEntityMenuItem('author');
      cy.get(entityDeleteButtonSelector).last().click();
      cy.get(entityConfirmDeleteButtonSelector).click();
      cy.wait('@deleteRequest').then(({ response }) => expect(response!.statusCode).to.equal(204));
    });
  });
});
