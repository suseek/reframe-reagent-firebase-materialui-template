'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');

module.exports = class extends Generator {
  prompting() {
    // Have Yeoman greet the user.
    this.log(
      yosay(`Welcome to the dandy ${chalk.red('generator-shadow-reframe-firebase-materialui')} generator!`)
    );

    const prompts = [
      {
        type: 'input',
        name: 'name',
        message: 'Your project name',
        default: this.name,
	store: true
      },
      {
        type: 'input',
        name: 'firebaseApiKey',
        message: 'Firebase apiKey, get it from Firebase -> Project Settings (near `Project Overview`) -> `Your apps` section -> Web apps -> Firebase SDK snippet -> Config',
        default: 'your-api-key',
	store: true
      },
      {
        type: 'input',
        name: 'firebaseAuthDomain',
        message: 'Firebase authDomain, get it from Firebase -> Project Settings (near `Project Overview`) -> `Your apps` section -> Web apps -> Firebase SDK snippet -> Config',
        default: 'your-app.firebaseapp.com',
	store: true
      },
      {
        type: 'input',
        name: 'firebaseDatabaseURL',
        message: 'Firebase databaseURL, get it from Firebase -> Project Settings (near `Project Overview`) -> `Your apps` section -> Web apps -> Firebase SDK snippet -> Config',
        default: 'https://your-db.firebaseio.com',
	store: true
      },
      {
        type: 'input',
        name: 'firebaseProjectId',
        message: 'Firebase projectId, get it from Firebase -> Project Settings (near `Project Overview`) -> `Your apps` section -> Web apps -> Firebase SDK snippet -> Config',
        default: 'your-project-id',
	store: true
      },
      {
        type: 'input',
        name: 'firebaseStorageBucket',
        message: 'Firebase storageBucket, get it from Firebase -> Project Settings (near `Project Overview`) -> `Your apps` section -> Web apps -> Firebase SDK snippet -> Config',
        default: 'your-app.appspot.com',
	store: true
      },
      {
        type: 'input',
        name: 'firebaseMessagingSenderId',
        message: 'Firebase messagingSenderId, get it from Firebase -> Project Settings (near `Project Overview`) -> `Your apps` section -> Web apps -> Firebase SDK snippet -> Config',
        default: 'messagingSenderId',
	store: true
      },
      {
        type: 'input',
        name: 'firebaseAppId',
        message: 'Firebase appId, get it from Firebase -> Project Settings (near `Project Overview`) -> `Your apps` section -> Web apps -> Firebase SDK snippet -> Config',
        default: 'appId',
	store: true
      }
    ];

    return this.prompt(prompts).then(props => {
      this.props = props;
    });
  }

  writing() {
    this.fs.copy(this.templatePath('README.md'), this.destinationPath('README.md'));
    this.fs.copy(this.templatePath('yarn.lock'), this.destinationPath('yarn.lock'));
    this.fs.copy(this.templatePath('firebase.json'), this.destinationPath('firebase.json'));
    this.fs.copy(this.templatePath('dist'), this.destinationPath('dist'));
    this.fs.copyTpl(this.templatePath('public'), this.destinationPath('public'), {
        name: this.props.name
    });
    this.fs.copy(this.templatePath('src/css'), this.destinationPath('src/css'));

    this.fs.copyTpl(this.templatePath('src/template'), this.destinationPath('src/' + this.props.name), {
        name: this.props.name,
        firebaseApiKey: this.props.firebaseApiKey,
        firebaseAuthDomain: this.props.firebaseAuthDomain,
        firebaseDatabaseURL: this.props.firebaseDatabaseURL,
        firebaseProjectId: this.props.firebaseProjectId,
        firebaseStorageBucket: this.props.firebaseStorageBucket,
        firebaseMessagingSenderId: this.props.firebaseMessagingSenderId,
        firebaseAppId: this.props.firebaseAppId
    });
    this.fs.copyTpl( this.templatePath('package.json'), this.destinationPath('package.json'), {
        name: this.props.name	    
    });
    this.fs.copyTpl( this.templatePath('shadow-cljs.edn'), this.destinationPath('shadow-cljs.edn'), {
        name: this.props.name	    
    });


  }

  install() {
    this.yarnInstall();
  }
};
