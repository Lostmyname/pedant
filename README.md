# pedant

An app that posts a checklist to a newly opened pull request. Like this:

![A PR checklist](https://dl.dropboxusercontent.com/u/1038218/deletenot/Staging2_by_tomcartwrightuk_%C2%B7_Pull_Request__1113_%C2%B7_Lostmyname_eagle.png "PR checklist")

To change the checklist that is posted, simply edit the `checklist.md` file in the repo root.
Inspired by this [tweet](https://twitter.com/leggetter/status/530319775554363393).

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Github setup

Add a new webhook for your repo pointing at the `/pull-request` on this app. Select the specific pull requst event to trigger the webhook.

![Setup a webhook on Github poitn at /pull-request](https://dl.dropboxusercontent.com/u/1038218/deletenot/Webhook_-_https___tomc_pagekite_me_pull-request.png "Webhook setup")

Generate a new `Personal access token` in Gihub and set this as the env variable `GITHUB_TOKEN`.

## Running

To start a web server for the application, run:

    lein ring server

# Deploying

To deploy to Heroku, simply create a new app, push and set your 'GITHUB_TOKEN' env var with

```
  heroku config:set GITHUB_TOKEN=<token>
```

The app is compiled in the `/jars` folder to improve the deployment speed so if you make changes to the `checklist.md` file, recompile with the following command and move the resulting standalone jar to the `jars` folder.

```
  lein ring uberjar
```

## License

MIT
