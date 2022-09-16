SHELL := /bin/sh

.PHONY: hooks lint lint-all

hooks:
	@pre-commit uninstall
	@echo "Clearing pre-commit cache... "
	@pre-commit clean
	@pre-commit install

lint:
	@echo "Checking STAGED files only..."
	@pre-commit

lint-all:
	@echo "Checking all TRACKED files..."
	@pre-commit run --all-files
