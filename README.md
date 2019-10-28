# Toy IOC and AOP

A **Sample** of Spring IoC and AOP.  Good for Java Starters


# rebind prefix key to C-x
unbind C-b
set -g prefix C-a
bind C-a send-prefix

# Start windows and panes at 1, not 0
set -g base-index 1
set -g pane-base-index 1
set-window-option -g automatic-rename on
set-option -g set-titles on
set-window-option -g window-status-current-bg red

# Use Alt-vim keys without prefix key to switch panes
bind -n M-h select-pane -L
bind -n M-j select-pane -D
bind -n M-k select-pane -U
bind -n M-l select-pane -R

# Use Alt-arrow keys without prefix key to switch panes
bind -n M-Left select-pane -L
bind -n M-Right select-pane -R
bind -n M-Up select-pane -U
bind -n M-Down select-pane -D

# User Alt-number to select pane
bind -n M-1 select-pane -t 1
bind -n M-2 select-pane -t 2
bind -n M-3 select-pane -t 3
bind -n M-4 select-pane -t 4

bind -n M-s setw synchronize-panes on
bind -n M-d setw synchronize-panes off


