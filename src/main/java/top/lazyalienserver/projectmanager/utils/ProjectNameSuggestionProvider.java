package top.lazyalienserver.projectmanager.utils;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;
import top.lazyalienserver.projectmanager.command.ProjectManager;
import top.lazyalienserver.projectmanager.define.Project;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

