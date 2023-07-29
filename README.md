# Create Project
- git init
- gh repo create sdjpa --source=.
- git add .
- git commit -m "....."
- git push --set-upstream origin master

# Cascade
- Cascade is hibernate feature.
- We are saying with this feature that is what we want to do for mapped objects
- For example I can say with persist cascade type, if i save main object, save mapped object too.

## Cascade Types
- PERSIST
  - if i save main object, save mapped object too.
- 