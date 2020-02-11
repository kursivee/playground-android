## Build Test

Messing around with gradle builds

### Release Experiment
Created scripts to help faciliate the release process

Scripts located in `/bin/`
- Tagging is done only when release branch goes into master
- Using a config file to set the version. Can do in the build.gradle but don't want to deal with file parsing. Maybe it's easier with build.gradle.kts?

#### Cut the release
- From develop branch, run `./release $release_name $release_version`
- Creates a release branch
- Updates version on release branch to `$release_version`
- Increments patch version of the `$release_version` and updates version on develop branch
  
#### Finish the release
- From release branch, run `./finish`
- Merges release branch into master
- Creates an empty commit and tags it based on the `$release_verion`
- Merges master into develop
