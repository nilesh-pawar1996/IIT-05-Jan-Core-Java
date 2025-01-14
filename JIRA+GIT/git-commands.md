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

## git internals

- git repository (.git directory)
  - HEAD
  - config
  - description
  - hooks
  - info
  - objects
    - on commit, git creates the objects (files) in this directory
    - there are 4 types of objects
      - commit object
        - created per commit
        - it contains the information
          - tree object id
          - author
          - committer
          - commit message
      - tree object
        - created per commit per directory
        - it contains the information
          - 100644: the entry is pointing to a file
          - blob: type of object
          - blob object id: the id of blob object
          - file name: the name of the file which is having the contents stored in the blob file
      - blob object
        - created per file
        - it contains the contents of a file in encrypted format (use git cat-file command to read the contents)
  - refs
    - heads
    - tgs

```bash

# get the contents of internal object
> git cat-file -p <object id>

```

## branch

- branch is a file under .git/refs/heads with branch name
- which stores the latest commit id
- the default branch name is either main or master

```bash

# get the list of branches
> git branch

# create a branch
# > git branch <branch name>
> git branch b1

# switch to a new branch
# > git checkout <branch name>
> git checkout b1

# create a new branch and checkout immediately
# > git checkout -b <new branch name>
> git checkout -b b2

# merge a branch in current branch
# > git merge <branch name>

# merge b1 in main
> git checkout main
> git merge b1

# delete a branch
# > git branch -d <branch name>
> git branch -d b1

```
