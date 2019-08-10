(ns template.config)

(def firebase {:apiKey "<%= firebaseApiKey %>"
               :authDomain "<%= firebaseAuthDomain %>"
               :databaseURL "<%= firebaseDatabaseURL %>"
               :projectId "<%= firebaseProjectId %>"
               :storageBucket "<%= firebaseStorageBucket %>"
               :messagingSenderId "<%= firebaseMessagingSenderId %>"})
