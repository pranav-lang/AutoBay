@echo off
for /r %%f in (.gitkeep) do (
    echo Deleting %%f
    del /f /q "%%f"
)
