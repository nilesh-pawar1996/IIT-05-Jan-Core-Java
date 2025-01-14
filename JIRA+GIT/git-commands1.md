# Git

## Foundation

- repository
  - the directory which contains the working directory along with the metadata (.git directory)

```bash

# initialize an empty repository
> git init

# get the current status of repository
> git status

# get the current status with short style
> git status -s
# status will be represented in two characters
# 1: status of the file against the staging area
# 2: status of the file against the working directory
# ??: the file is untracked (the file is not known to the repository or the repository has not yet created any version of this file)
# A : the changes to the file are added to the staging area (staged file(s))
#  M: the file is modified and present in working directory
# M : the file is modified and present in staging area
#
#

# add the changes made to a file to the staging area
> git add <file name>

# add all the changes in the current directory to staging area
> git add .

# commit the changes to the repository
# - a new version will be recorded by the repository
# - any version in repository will contain
#   - author (user who has committed the changes)
#   - date and time of commit
#   - message used while committing the changes
#   - actual changes (added and deleted lines/files)
# > git commit -m <commit message>
> git commit -m "version 1"

# get all the commit logs
> git log

# get all the commit logs
# --online: show only one line description
# --color: show colors while showing the logs
# --graph: show the branch graph
> git log --oneline --color --graph

# get the difference of current version of a file with last recorded version
> git diff

# remove the changes by replacing the last recorded version with the current version
> git checkout <file name>

# unstage the changes
# remove the changes from staging area and move them back to the working directory
> git reset

# unstage the changes and remove them immediately (along with all the changes in the working directory)
# NOTE: please execute this command on your own risk
> git reset --hard

```
